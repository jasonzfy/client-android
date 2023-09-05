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

package xyz.eulix.space.network.agent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import xyz.eulix.space.bean.bind.InitResponseNetwork;
import xyz.eulix.space.interfaces.EulixKeep;

/**
 * @author: chenjiawei
 * Description:
 * date: 2021/11/18 10:14
 */
public class NetworkInfo implements Serializable, EulixKeep {
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;
    @SerializedName("network")
    private List<InitResponseNetwork> networks;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InitResponseNetwork> getNetworks() {
        return networks;
    }

    public void setNetworks(List<InitResponseNetwork> networks) {
        this.networks = networks;
    }

    @Override
    public String toString() {
        return "NetworkInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", networks=" + networks +
                '}';
    }
}
