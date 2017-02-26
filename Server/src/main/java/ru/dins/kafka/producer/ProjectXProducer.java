package ru.dins.kafka.producer;

import ru.dins.web.model.quote.Quote;

import java.io.Closeable;

/**
 * Created by gnupinguin on 20.02.17.
 */
public interface ProjectXProducer extends Closeable{
    void addQuoteInQueue(Quote quote);

    @Override
    void close();

}
