package org.eclipse.rap.rwt.performance.phase;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.StopWatch;
import org.eclipse.rwt.lifecycle.PhaseEvent;
import org.eclipse.rwt.lifecycle.PhaseId;
import org.eclipse.rwt.lifecycle.PhaseListener;

public class PerformancePhaseListener implements PhaseListener {

  private static final long serialVersionUID = 1L;
  private StopWatch meter;

  public void beforePhase( final PhaseEvent event ) {
    // TODO [rst] Not clear what this was needed for
    TestCase testCase = new TestCase() {
      public String getName() {
        return "foo#" + event.getPhaseId();
      }
    };
    meter = new StopWatch();
    meter.start();
  }

  public void afterPhase( PhaseEvent event ) {
    meter.stop();
    // TODO [rst] Do something with the results
    meter.getResults();
  }

  public PhaseId getPhaseId() {
    return PhaseId.ANY;
  }
}
