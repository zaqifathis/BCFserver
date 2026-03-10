package de.openfabtwin.utils;

import java.io.IOException;
import java.io.InputStream;

public class FakeClosingInputStream extends InputStream {
    private final InputStream delegate;

    public FakeClosingInputStream(InputStream delegate) {
        this.delegate = delegate;
    }

    @Override
    public int read() throws IOException {
        return delegate.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return delegate.read(b, off, len);
    }

    @Override
    public void close() {}
}