package com.example.demo.mock.server.util;

import com.example.demo.mock.server.domain.record.InMemoryStoredRecord;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.StorageQualifiers;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * Created by OTARANOVSKYI on 02.08.2017.
 */
@Component
public class CallBackInitializer {

    private static SynchronousQueue<InMemoryStoredRecord> records = new SynchronousQueue<>();

    @Autowired
    @Qualifier(StorageQualifiers.IN_MEMORY)
    private RequestResponseStorage requestResponseStorage;

    @PostConstruct
    public void init() {
        final RequestResponseStorage requestResponseStorage = this.requestResponseStorage;

//        takeJava8Way(requestResponseStorage);

        takeObservableWay(requestResponseStorage);
    }

//    private void takeJava8Way(final RequestResponseStorage requestResponseStorage) {
//        Stream.<InMemoryStoredRecord>generate(new Supplier<InMemoryStoredRecord>() {
//            @Override
//            public InMemoryStoredRecord get() {
//                return records.poll();
//            }
//        }).forEach(new Consumer<InMemoryStoredRecord>() {
//            @Override
//            public void accept(InMemoryStoredRecord inMemoryStoredRecord) {
//                requestResponseStorage.saveRequestAndResponse(
//                        inMemoryStoredRecord.getRequest(),
//                        inMemoryStoredRecord.getResponse(),
//                        inMemoryStoredRecord.getDelay()
//                );
//            }
//        });
//    }

    private Thread takeObservableWay(final RequestResponseStorage requestResponseStorage) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Observable.<InMemoryStoredRecord>fromIterable(
                        new Iterable<InMemoryStoredRecord>() {
                            @Override
                            public Iterator<InMemoryStoredRecord> iterator() {
                                return new Iterator<InMemoryStoredRecord>() {
                                    @Override
                                    public boolean hasNext() {
                                        return true;
                                    }

                                    @Override
                                    public InMemoryStoredRecord next() {
                                        try {
                                            return records.take();
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                };
                            }
                        }
                ).safeSubscribe(new Observer<InMemoryStoredRecord>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InMemoryStoredRecord inMemoryStoredRecord) {
                        requestResponseStorage.saveRequestAndResponse(
                                inMemoryStoredRecord.getRequest(),
                                inMemoryStoredRecord.getResponse(),
                                inMemoryStoredRecord.getDelay()
                        );
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
        thread.setDaemon(true);
        thread.start();

        return thread;
    }

    public static void push(HttpRequest httpRequest, HttpResponse httpResponse, Long delay) {
        InMemoryStoredRecord inMemoryStoredRecord = new InMemoryStoredRecord();
        inMemoryStoredRecord.setRequest(httpRequest);
        inMemoryStoredRecord.setResponse(httpResponse);
        inMemoryStoredRecord.setDelay(delay);

        try {
            records.put(inMemoryStoredRecord);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
