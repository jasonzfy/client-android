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

package xyz.eulix.space.bean;

/**
 * @author: chenjiawei
 * Description:
 * date: 2021/8/26 11:44
 */
public class GatewayCommunicationBase {
    private String boxUuid;
    private String boxBind;
    private String accessToken;
    private String secretKey;
    private String boxDomain;
    private Long tokenExpire;
    private String transformation;
    private String ivParams;

    public String getBoxUuid() {
        return boxUuid;
    }

    public void setBoxUuid(String boxUuid) {
        this.boxUuid = boxUuid;
    }

    public String getBoxBind() {
        return boxBind;
    }

    public void setBoxBind(String boxBind) {
        this.boxBind = boxBind;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBoxDomain() {
        return boxDomain;
    }

    public void setBoxDomain(String boxDomain) {
        this.boxDomain = boxDomain;
    }

    public Long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public String getIvParams() {
        return ivParams;
    }

    public void setIvParams(String ivParams) {
        this.ivParams = ivParams;
    }

    @Override
    public String toString() {
        return "GatewayCommunicationBase{" +
                "boxUuid='" + boxUuid + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", boxDomain='" + boxDomain + '\'' +
                ", tokenExpire=" + tokenExpire +
                ", transformation='" + transformation + '\'' +
                ", ivParams='" + ivParams + '\'' +
                '}';
    }
}
