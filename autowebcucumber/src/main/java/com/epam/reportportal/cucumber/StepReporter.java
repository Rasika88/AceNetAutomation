//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.epam.reportportal.cucumber;

import com.epam.reportportal.service.Launch;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Step;
import io.reactivex.Maybe;
import java.util.Calendar;

public class StepReporter extends AbstractReporter {
    protected Maybe<String> currentStepId = null;
    protected Maybe<String> hookStepId = null;
    protected String hookStatus = null;

    public StepReporter() {
    }

    protected Maybe<String> getRootItemId() {
        return null;
    }

    protected void beforeStep(Step step) {
        StartTestItemRQ rq = new StartTestItemRQ();
        rq.setName(Utils.buildStatementName(step, this.stepPrefix, " ", (String)null));
        rq.setDescription(Utils.buildMultilineArgument(step));
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setType("STEP");
        this.currentStepId = ((Launch)this.RP.get()).startTestItem(this.currentScenario.getId(), rq);
    }

    protected void afterStep(Result result) {
        this.reportResult(result, (String)null);
        Utils.finishTestItem((Launch)this.RP.get(), this.currentStepId, Utils.mapStatus(result.getStatus()));
        this.currentStepId = null;
    }

    protected void beforeHooks(Boolean isBefore) {
      /*  StartTestItemRQ rq = new StartTestItemRQ();
        rq.setName(isBefore ? "Before hooks" : "After hooks");
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setType(isBefore ? "BEFORE_TEST" : "AFTER_TEST");
        this.hookStepId = ((Launch)this.RP.get()).startTestItem(this.currentScenario.getId(), rq);
        this.hookStatus = "PASSED";*/
    }

    protected void afterHooks(Boolean isBefore) {
        /*Utils.finishTestItem((Launch)this.RP.get(), this.hookStepId, this.hookStatus);
        this.hookStepId = null;*/
    }

    protected void hookFinished(Match match, Result result, Boolean isBefore) {
      /*  this.reportResult(result, (isBefore ? "Before" : "After") + " hook: " + match.getLocation());
        if (result.getStatus().equals("failed")) {
            this.hookStatus = "FAILED";
        }*/

    }
    protected String getFeatureTestItemType() {
        return "SUITE";
    }

    protected String getScenarioTestItemType() {
        return "SCENARIO";
    }
}
