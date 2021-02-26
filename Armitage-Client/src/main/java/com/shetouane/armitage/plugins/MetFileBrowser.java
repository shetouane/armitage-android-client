package com.shetouane.armitage.plugins;

import java.util.Map;

import org.msgpack.type.Value;

import android.content.Context;

import com.shetouane.armitage.console.ConsoleSession.ConsoleSessionParams;
import com.shetouane.armitage.console.ControlSession;

public class MetFileBrowser extends ControlSession {

	MetFileBrowser(Context context, String type, String id,
			Map<String, Value> info, ConsoleSessionParams params) {
		super(context, type, id, info, params);
	}

	MetFileBrowser(Context context, String type, String id,
			Map<String, Value> info) {
		super(context, type, id, info);
	}

}
