package com.testRailsbtListener.testRail;


import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;


public class TestRailHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private APIClient client = null;

    private TestRailHelper() {
        // private constructor
    }

    private static class TestRailHelperHolder {
        public static final TestRailHelper INSTANCE = new TestRailHelper();
    }

    public static TestRailHelper getInstance() {
        return TestRailHelperHolder.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

    public void authenticate(String username, String password, String url) {
        client = new APIClient(url);
        client.setUser(username);
        client.setPassword(password);
    }

    public JSONObject createRun(Integer projectId, Map<String, Object> data)
            throws MalformedURLException, IOException, APIException {
        JSONObject response = (JSONObject) client.sendPost(
                String.format("add_run/%d", projectId), data);
        return response;
    }

    public JSONObject createRun(Integer projectId, Integer suiteId,
                                String name, String description) throws MalformedURLException,
            IOException, APIException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("suite_id", suiteId);
        data.put("name", name);
        data.put("description", description);
        data.put("include_all", new Boolean(true));

        return createRun(projectId, data);
    }

    public JSONObject updateRun(Integer runId, Map<String, Object> data)
            throws MalformedURLException, IOException, APIException {
        JSONObject response = (JSONObject) client.sendPost(
                String.format("update_run/%d", runId), data);
        return response;

    }
    public JSONObject updateRun(Integer runId,
                                String name, String description) throws MalformedURLException,
            IOException, APIException {
        //updateCaseStatus(1461, 239247, 1, "");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", name);
        data.put("description", description);
        data.put("include_all", new Boolean(true));

        return updateRun(runId, data);
    }

    public JSONObject updateCaseStatus(Integer runId, Integer caseId,
                                       Map<String, Object> data) throws MalformedURLException,
            IOException, APIException {
        JSONObject response = (JSONObject) client
                .sendPost(String.format("add_result_for_case/%d/%d", runId,
                        caseId), data);
        return response;
    }

    public JSONObject updateCaseStatus(Integer runId, Integer caseId,
                                       Integer statusId, String comment) throws MalformedURLException,
            IOException, APIException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("status_id", statusId);
        data.put("comment", comment);

        return updateCaseStatus(runId, caseId, data);
    }

}
