package pl.recompiled.jwtspringsecuritydemo;

import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;

import java.util.List;

@Slf4j
public class QueryLoggingListener implements QueryExecutionListener {

    @Override
    public void beforeQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {

    }

    @Override
    public void afterQuery(ExecutionInfo executionInfo, List<QueryInfo> list) {
        for (QueryInfo queryInfo: list) {
            log.info("Query: " + queryInfo.getQuery());
        }
    }
}
