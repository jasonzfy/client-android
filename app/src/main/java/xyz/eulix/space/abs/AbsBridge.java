/*
 * Copyright (c) 2022 Institute of Software, Chinese Academy of Sciences (ISCAS)
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

package xyz.eulix.space.abs;

/**
 * @author: chenjiawei
 * Description:
 * date: 2021/10/28 13:35
 */
public abstract class AbsBridge {
    protected SourceCallback mSourceCallback;
    protected SinkCallback mSinkCallback;

    protected interface SourceCallback {}

    protected interface SinkCallback {}

    public void registerSourceCallback(SourceCallback callback) {
        mSourceCallback = callback;
    }

    public void unregisterSourceCallback() {
        mSourceCallback = null;
    }

    public void registerSinkCallback(SinkCallback callback) {
        mSinkCallback = callback;
    }

    public void unregisterSinkCallback() {
        mSinkCallback = null;
    }
}
