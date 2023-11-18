package com.app.core.user.payload;

import lombok.Builder;

@Builder
public record UserPayload(String name, Integer age) {
}
