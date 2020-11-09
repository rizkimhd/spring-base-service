package com.rizkimhd.learning.spring.controller.filter.helper;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import org.apache.commons.io.output.TeeOutputStream;

/**
 * This implementation was based on
 *
 * @see <a href="https://stackoverflow.com/questions/33744875/spring-boot-how-to-log-all-requests-and-responses-with-exceptions-in-single-pl">
 * https://stackoverflow.com
 * </a>
 */
public class TeeServletOutputStream extends ServletOutputStream {

  private final TeeOutputStream targetStream;

  public TeeServletOutputStream(OutputStream one, OutputStream two) {
    targetStream = new TeeOutputStream(one, two);
  }

  @Override
  public void write(int arg0) throws IOException {
    this.targetStream.write(arg0);
  }

  public void flush() throws IOException {
    super.flush();
    this.targetStream.flush();
  }

  public void close() throws IOException {
    super.close();
    this.targetStream.close();
  }

  @Override
  public boolean isReady() {
    return false;
  }

  @Override
  public void setWriteListener(WriteListener writeListener) {

  }
}