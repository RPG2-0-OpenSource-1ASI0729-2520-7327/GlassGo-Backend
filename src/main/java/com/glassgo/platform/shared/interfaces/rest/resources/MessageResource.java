package com.glassgo.platform.shared.interfaces.rest.resources;

/**
 * Generic message resource used across the REST API.
 * <p>
 * This simple resource is commonly returned by endpoints that need to provide
 * a human-readable message, for example after successful operations or error
 * responses.
 */
public record MessageResource(String message) {
}
