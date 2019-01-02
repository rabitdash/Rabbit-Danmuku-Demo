package com.rabyte.rabitdash.Service;

import com.ilargia.games.entitas.Context;
import com.rabyte.rabitdash.util.DMKDebug;
//TODO
public class AWTDebugLogService implements ILogService {
    @Override
    public void LogMessage(String message) {
        System.out.println(message);
    }
}
