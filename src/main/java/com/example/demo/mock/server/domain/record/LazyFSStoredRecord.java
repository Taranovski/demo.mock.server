package com.example.demo.mock.server.domain.record;

import com.example.demo.mock.server.domain.StoredRecord;
import com.google.common.base.Strings;
import org.apache.commons.lang3.tuple.Pair;
import org.mockserver.model.Body;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.NottableString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.example.demo.mock.server.repository.impl.FileSystemRecordingStorage.REQUEST_FILE_NAME;
import static com.example.demo.mock.server.repository.impl.FileSystemRecordingStorage.RESPONSE_FILE_NAME;

public class LazyFSStoredRecord implements StoredRecord {
    private final Path folder;
    private final LazyHttpRequest lazyRequest;
    private final Path path;

    public LazyFSStoredRecord(Pair<Path, Path> paths) {
        folder = paths.getLeft();
        path = paths.getRight();
        lazyRequest = new LazyHttpRequest();
    }

    @Override
    public HttpRequest getRequest() {
        return lazyRequest;
    }

    @Override
    public HttpResponse getResponse() {
        return HttpResponse.response(makeBodyFromFile(this.folder, RESPONSE_FILE_NAME));
    }

    @Override
    public Long getDelay() {
        return 0L;
    }

    public long getBodyHash() {
        return extractHashValueFromPath(path);
    }

    public static Long extractHashValueFromPath(Path relativeFolder) {
        return Long.valueOf(relativeFolder.getName(relativeFolder.getNameCount() - 1).toString());
    }

    public static String makePathFromFolder(Path folder) {
        return "/" + folder.toString().replaceAll("\\\\+", "/");
    }

    public static String makeBodyFromFile(Path path, String filename) {
        try {
            return Strings.emptyToNull(new String(Files.readAllBytes(path.resolve(filename))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public class LazyHttpRequest extends HttpRequest {
        @Override
        public NottableString getPath() {
            withPath(makePathFromFolder(path.getParent()));
            return super.getPath();
        }

        @Override
        public Body getBody() {
            withBody(makeBodyFromFile(folder, REQUEST_FILE_NAME));
            return super.getBody();
        }
    }
}
