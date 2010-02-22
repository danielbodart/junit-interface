package com.novocode.junit;

import org.junit.runner.Description;
import org.scalatools.testing.Result;


final class TestPassedEvent extends AbstractEvent
{
  TestPassedEvent(Description desc) { super(buildName(desc), null, Result.Success, null); }

  @Override
  public void logTo(RichLogger logger)
  {
    logger.info("Test "+testName+" passed");
  }
}
