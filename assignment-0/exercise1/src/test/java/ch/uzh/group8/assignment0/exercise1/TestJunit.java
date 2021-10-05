package ch.uzh.group8.assignment0.exercise1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

class TestJunit {
  @Test
  public void a_test_works() {
    assertThat(1, is(1));
  }
}
