/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.mai.fam.util.exception;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Класс, расширяющий ResponseStatusException добавляя код (code) ошибки и ее текст (error).
 * <p>
 * Коды ошибок и их тексты находятся в классе {@link RestErrorCodes}.
 */
public class ResponceStatusExceptionWithCode extends ResponseStatusException implements HasErrorCode {

    @Nullable
    private String code;

    @Nullable
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public ResponceStatusExceptionWithCode(HttpStatus status, String code) {
        super(status);
        this.code = code;
    }

    public ResponceStatusExceptionWithCode(HttpStatus status, String reason, String code) {
        super(status, reason);
        this.code = code;
    }

    public ResponceStatusExceptionWithCode(HttpStatus status, String reason, Throwable cause, String code) {
        super(status, reason, cause);
        this.code = code;
    }

    public ResponceStatusExceptionWithCode(HttpStatus status, Throwable cause, String code) {
        super(status, cause.getMessage(), cause);
        this.code = code;
    }

    @Override
    public Optional<String> getError() {
        if (code != null) {
            return Optional.ofNullable(RestErrorCodes.getError(code));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String getMessage() {
        String msg = getStatus() + (code != null ? " [" + code + "]": "")
            + (getReason() != null ? " \"" + getReason() + "\"" : "");
        return NestedExceptionUtils.buildMessage(msg, getCause());
    }

}
