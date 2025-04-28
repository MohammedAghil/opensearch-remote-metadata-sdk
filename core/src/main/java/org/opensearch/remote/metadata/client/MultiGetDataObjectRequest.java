package org.opensearch.remote.metadata.client;

/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

import org.opensearch.core.common.Strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A class abstracting an OpenSearch MultiGetRequest
 * Used to retrieve multiple documents in a single request
 */
public class MultiGetDataObjectRequest {
    private final List<GetDataObjectRequest> requests;

    /**
     * Instantiate this request with a list of GetDataObjectRequest objects
     * <p>
     * All requests must have the same tenantId and valid index/id values
     * @param requests the list of GetDataObjectRequest objects to include in this multi-get request
     */
    public MultiGetDataObjectRequest(List<GetDataObjectRequest> requests) {
        this.requests = requests != null ? new ArrayList<>(requests) : new ArrayList<>();
        validateRequests();
    }

    /**
     * Get the list of GetDataObjectRequest objects in this multi-get request
     * @return an unmodifiable list of GetDataObjectRequest objects
     */
    public List<GetDataObjectRequest> requests() {
        return Collections.unmodifiableList(requests);
    }

    /**
     * Validates that all requests have the same tenantId and valid index/id values
     * @throws IllegalArgumentException if validation fails
     */
    private void validateRequests() {
        if (requests.isEmpty()) {
            return;
        }
        // Ensure all requests have the same tenantId and valid index/id
        String tenantId = requests.get(0).tenantId();
        for (GetDataObjectRequest request : requests) {
            if (!Objects.equals(tenantId, request.tenantId())) {
                throw new IllegalArgumentException("All GetDataObjectRequest objects must have the same tenantId");
            }
            if (Strings.isNullOrEmpty(request.index())) {
                throw new IllegalArgumentException("Index must be specified for GetDataObjectRequest");
            }
            if (Strings.isNullOrEmpty(request.id())) {
                throw new IllegalArgumentException("ID must be specified for GetDataObjectRequest");
            }
        }
    }

    /**
     * Instantiate a builder for this object
     * @return a builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for MultiGetDataObjectRequest
     */
    public static class Builder {
        private List<GetDataObjectRequest> requests = new ArrayList<>();

        /**
         * Add a GetDataObjectRequest to this multi-get request
         * @param request the GetDataObjectRequest to add
         * @return this builder
         */
        public Builder add(GetDataObjectRequest request) {
            this.requests.add(request);
            return this;
        }

        /**
         * Build the MultiGetDataObjectRequest
         * @return a new MultiGetDataObjectRequest instance
         */
        public MultiGetDataObjectRequest build() {
            return new MultiGetDataObjectRequest(requests);
        }
    }
}
