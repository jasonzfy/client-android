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

package xyz.eulix.space.network.files;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import xyz.eulix.space.interfaces.EulixKeep;

/**
 * @author: chenjiawei
 * Description:
 * date: 2021/8/25 10:42
 */
public class BaseResponseResult implements EulixKeep, Serializable {
    @SerializedName("affectRows")
    public int affectRows;
    @SerializedName("taskId")
    public String taskId;
    @SerializedName("taskStatus")
    public String taskStatus;
    @SerializedName("processed")
    public int processed;
    @SerializedName("total")
    public int total;

    @Override
    public String toString() {
        return "BaseResponseResult{" +
                "affectRows=" + affectRows +
                ", taskId='" + taskId + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                ", processed=" + processed +
                ", total=" + total +
                '}';
    }
}
