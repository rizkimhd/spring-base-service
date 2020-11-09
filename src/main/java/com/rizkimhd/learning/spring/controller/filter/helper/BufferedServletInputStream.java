package com.rizkimhd.learning.spring.controller.filter.helper;

import java.io.ByteArrayInputStream;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

/**
 * This implementation was based on
 *
 * @see <a href="https://stackoverflow.com/questions/33744875/spring-boot-how-to-log-all-requests-and-responses-with-exceptions-in-single-pl">
 * https://stackoverflow.com
 * </a>
 */
public class BufferedServletInputStream extends ServletInputStream {

  private final ByteArrayInputStream bais;

  public BufferedServletInputStream(ByteArrayInputStream bais) {
    this.bais = bais;
  }

  @Override
  public int available() {
    return this.bais.available();
  }

  @Override
  public int read() {
    return this.bais.read();
  }

  @Override
  public int read(byte[] buf, int off, int len) {
    return this.bais.read(buf, off, len);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public boolean isReady() {
    return true;
  }

  @Override
  public void setReadListener(ReadListener readListener) {

  }
}
