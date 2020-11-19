//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.epam.reportportal.cucumber;

import com.epam.reportportal.service.Launch;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.log.SaveLogRQ.File;
import gherkin.formatter.model.Match;
import gherkin.formatter.model.Result;
import gherkin.formatter.model.Step;
import io.reactivex.Maybe;
import java.util.Calendar;
import rp.com.google.common.base.Supplier;
import rp.com.google.common.base.Suppliers;

public class ScenarioReporter extends AbstractReporter {
    private static final String SEPARATOR = "-------------------------";
    protected Supplier<Maybe<String>> rootSuiteId = Suppliers.memoize(new Supplier<Maybe<String>>() {
        public Maybe<String> get() {
            StartTestItemRQ rq = new StartTestItemRQ();
            rq.setName("Root User Story");
            rq.setStartTime(Calendar.getInstance().getTime());
            rq.setType("STORY");
            return ((Launch)ScenarioReporter.this.RP.get()).startTestItem(rq);
        }
    });

    public ScenarioReporter() {
    }

    protected void beforeStep(Step step) {
        String decoratedStepName = this.decorateMessage(Utils.buildStatementName(step, this.stepPrefix, " ", (String)null));
        String multilineArg = Utils.buildMultilineArgument(step);
        Utils.sendLog(decoratedStepName + multilineArg, "INFO", (File)null);
    }

    protected void afterStep(Result result) {
        this.reportResult(result, this.decorateMessage("STEP " + result.getStatus().toUpperCase()));
    }

    protected void beforeHooks(Boolean isBefore) {
    }

    protected void afterHooks(Boolean isBefore) {
    }

    protected void hookFinished(Match match, Result result, Boolean isBefore) {
       // this.reportResult(result, (String)null);
    }

    protected String getFeatureTestItemType() {
        return "TEST";
    }

    protected String getScenarioTestItemType() {
        return "STEP";
    }

    protected Maybe<String> getRootItemId() {
        return (Maybe)this.rootSuiteId.get();
    }

    protected void afterLaunch() {
        Utils.finishTestItem((Launch)this.RP.get(), (Maybe)this.rootSuiteId.get());
        this.rootSuiteId = null;
        super.afterLaunch();
    }

    private String decorateMessage(String message) {
        return "--" + message + "--";
    }
}
