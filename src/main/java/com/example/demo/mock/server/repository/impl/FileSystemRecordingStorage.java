package com.example.demo.mock.server.repository.impl;

import com.example.demo.mock.server.domain.record.LazyFSStoredRecord;
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
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Qualifier(StorageQualifiers.FS_STORED)
public class FileSystemRecordingStorage implements RequestResponseStorage<LazyFSStoredRecord> {

    public static final String REQUEST_FILE_NAME = "request.xml";
    public static final String RESPONSE_FILE_NAME = "response.xml";

    private static final Path storage = Paths.get("storage").toAbsolutePath();

    private static void put(HttpRequest requestData, HttpResponse responseData) {
        File folder = new File(storage.toFile(), requestData.getPath().getValue());
        folder = new File(folder, String.valueOf(Strings.nullToEmpty(requestData.getBodyAsString()).hashCode()));
        folder = new File(folder, String.valueOf(getLastId(folder) + 1));
        if (!folder.exists() && !folder.mkdirs())
            throw new RuntimeException("Can't create directory:" + folder.toString());
        save(folder, REQUEST_FILE_NAME, requestData.getBodyAsString());
        save(folder, RESPONSE_FILE_NAME, responseData.getBodyAsString());
    }

    private static void save(File folder, String fileName, String value) {
        try (Writer writer = new FileWriter(new File(folder, fileName))) {
            writer.write(Strings.nullToEmpty(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long getLastId(File folder) {
        return Optional.ofNullable(folder.listFiles())
                .map(Arrays::stream)
                .orElse(Stream.empty())
                .filter(File::isDirectory)
                .mapToLong(value -> Long.valueOf(value.getName()))
                .max()
                .orElse(-1);
    }

    @Override
    public void saveRequestAndResponse(HttpRequest requestData, HttpResponse responseData, long delay) {
        put(requestData, responseData);
    }

    @Override
    @SuppressWarnings("unchecked")//FileUtils.listFiles returns not generified Collection
    public Stream<LazyFSStoredRecord> getAllRecords() {
        Collection<File> files = FileUtils.listFiles(storage.toFile(), new String[]{"xml"}, true);
        return files.stream()
                .map(File::getParentFile)
                .distinct()
                .map(File::toURI)
                .map(Paths::get)
                .map(Path::toAbsolutePath)
                .map(path -> Pair.of(path, storage.relativize(path.getParent())))
                .map(LazyFSStoredRecord::new);
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
