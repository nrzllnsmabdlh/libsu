/*
 * Copyright 2018 John "topjohnwu" Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.topjohnwu.superuser;

import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

class Utils {

    static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    static final String UPPER_CASE = LOWER_CASE.toUpperCase();
    static final String NUMBERS = "0123456789";
    static final String ALPHANUM = LOWER_CASE + UPPER_CASE + NUMBERS;

    static CharSequence genRandomAlphaNumString(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
        }
        return builder;
    }

    static boolean hasFlag(int flag) {
        return hasFlag(Shell.flags, flag);
    }

    static boolean hasFlag(int flags, int flag) {
        return (flags & flag) != 0;
    }

    static void log(String tag, Object log) {
        if (hasFlag(Shell.FLAG_VERBOSE_LOGGING))
            Log.d(tag, log.toString());
    }

    static void stackTrace(Throwable t) {
        if (hasFlag(Shell.FLAG_VERBOSE_LOGGING))
            t.printStackTrace();
    }

    static boolean onMainThread() {
        return ((Looper.myLooper() != null) && (Looper.myLooper() == Looper.getMainLooper()));
    }

    static void cleanInputStream(InputStream in) {
        try {
            while (in.available() != 0)
                in.skip(in.available());
        } catch (IOException ignored) {}
    }
}
