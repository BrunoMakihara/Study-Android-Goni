/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gcm.demo.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Datastore {


	private static final List<RegData> regIds = new ArrayList<RegData>();
	private static final Logger logger =
			Logger.getLogger(Datastore.class.getName());

	private static RegData data = null;
	private Datastore() {
		throw new UnsupportedOperationException();
	}

	public static void register(String regId, String tel) {
		logger.info("Registering " + regId);

		data = new RegData();
		data.setRegId(regId);
		data.setTel(tel);
		regIds.add(data);

	}
	public static void unregister(String regId) {
		logger.info("Unregistering " + regId);
		for(RegData d : regIds){
			if(d.getRegId().equals(regId)){
				regIds.remove(d);
			}
		}
	}
	public static List<RegData> getDevices() {
		return regIds;
	}


}
