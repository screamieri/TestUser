package it.andreascrimieri.test.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class RestResponse<T> {
    private Integer code;
    private String message;
    private T content;

    public RestResponse(Integer code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestResponse<?> that = (RestResponse<?>) o;
        return Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, content);
    }

    @Override
    public String toString() {
        return "RestResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
