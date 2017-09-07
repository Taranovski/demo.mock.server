package com.example.demo.mock.server.repository.impl;

import com.example.demo.mock.server.domain.StoredRecord;
import com.example.demo.mock.server.domain.record.InMemoryStoredRecord;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.StorageQualifiers;
import com.google.common.base.Strings;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Qualifier(StorageQualifiers.FS_STORED)
public class FileSystemRecordingStorage implements RequestResponseStorage<StoredRecord> {

    private static final String REQUEST_FILE_NAME = "request.xml";
    private static final String RESPONSE_FILE_NAME = "response.xml";

    private Path storage = Paths.get("storage");

    private void put(HttpRequest requestData, HttpResponse responseData, long delay) {
        try {
            String requestPath = requestData.getPath().getValue();
            File folder = new File(storage.toFile(), requestPath);
            folder = new File(folder, String.valueOf(getLastId(folder) + 1));
            save(folder, REQUEST_FILE_NAME, requestData.getBodyAsString());
            save(folder, RESPONSE_FILE_NAME, responseData.getBodyAsString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save(File folder, String fileName, String value) throws IOException {
        folder.mkdirs();
        try (Writer writer = new FileWriter(new File(folder, fileName))) {
            writer.write(Strings.nullToEmpty(value));
        }
    }

    private InMemoryStoredRecord get(Pair<Path, Path> currentPath) {
        try {
            InMemoryStoredRecord record = new InMemoryStoredRecord();
            HttpRequest request = HttpRequest.request(makePathFromFile(currentPath.getRight()));
            request.withBody(makeBodyFromFile(currentPath.getLeft(), REQUEST_FILE_NAME));
            record.setRequest(request);
            record.setResponse(HttpResponse.response(makeBodyFromFile(currentPath.getLeft(), RESPONSE_FILE_NAME)));
            return record;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String makePathFromFile(Path folder) {
        return "/"+folder.getParent().toString().replaceAll("\\\\+", "/");
    }

    private String makeBodyFromFile(Path path, String filename) throws IOException {
        return Strings.emptyToNull(new String(Files.readAllBytes(path.resolve(filename))));
    }

    private long getLastId(File folder) {
        File[] array = folder.listFiles();
        if (array == null) {
            return 0;
        }
        return Arrays.stream(array)
                .filter(File::isDirectory)
                .mapToLong(value -> Long.valueOf(value.getName()))
                .max()
                .orElse(-1);
    }

    @Override
    public void saveRequestAndResponse(HttpRequest requestData, HttpResponse responseData, long delay) {
        put(requestData, responseData, delay);
    }

    @Override
    public List<StoredRecord> getAllRecords() {
        Collection<File> collection = FileUtils.listFiles(storage.toFile(), new String[]{"xml"}, true);
        return collection.stream()
                .map(File::getParentFile)
                .distinct()
                .map(File::toURI)
                .map(Paths::get)
                .map(Path::toAbsolutePath)
                .map(path -> Pair.of(path, storage.toAbsolutePath().relativize(path)))
                .map(this::get)
                .collect(Collectors.toList());
    }

    @Override
    public void reset() {
        try {
            Files.delete(storage);
            Files.createDirectory(storage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
