package org.opensearch.remote.metadata.client;

/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 */

import org.opensearch.core.xcontent.XContentParser;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the response from a multi-get operation
 * Contains an array of individual DataObjectResponse objects
 */
public class MultiGetDataObjectResponse {
    private final DataObjectResponse[] responses;
    private final XContentParser parser;

    /**
     * Instantiate this response with an array of DataObjectResponse objects and a parser
     * @param responses the array of individual responses
     * @param parser the XContentParser for this response
     */
    public MultiGetDataObjectResponse(DataObjectResponse[] responses, XContentParser parser) {
        this.responses = responses;
        this.parser = parser;
    }

    /**
     * Get the array of individual DataObjectResponse objects
     * @return the array of responses
     */
    public DataObjectResponse[] getResponses() {
        return responses;
    }

    /**
     * Get the XContentParser for this response
     * @return the parser
     */
    public XContentParser parser() {
        return parser;
    }

    /**
     * Builder class for MultiGetDataObjectResponse
     */
    public static class Builder {
        private List<DataObjectResponse> responses = new ArrayList<>();
        private XContentParser parser;

        /**
         * Add a DataObjectResponse to this multi-get response
         * @param response the DataObjectResponse to add
         * @return this builder
         */
        public Builder response(DataObjectResponse response) {
            this.responses.add(response);
            return this;
        }

        /**
         * Set the XContentParser for this response
         * @param parser the XContentParser
         * @return this builder
         */
        public Builder parser(XContentParser parser) {
            this.parser = parser;
            return this;
        }

        /**
         * Build the MultiGetDataObjectResponse
         * @return a new MultiGetDataObjectResponse instance
         */
        public MultiGetDataObjectResponse build() {
            return new MultiGetDataObjectResponse(responses.toArray(new DataObjectResponse[0]), parser);
        }
    }
}
