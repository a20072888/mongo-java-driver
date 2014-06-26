/*
 * Copyright (c) 2008 - 2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb;

import org.bson.codecs.Decoder;
import org.mongodb.MongoCommandFailureException;

final class MongoExceptions {
    public static com.mongodb.MongoException mapException(final org.mongodb.MongoException e) {
        return mapException(e, DBObjects.codec);
    }

    @SuppressWarnings("deprecation")
    public static com.mongodb.MongoException mapException(final org.mongodb.MongoException e, final Decoder<DBObject> decoder) {
        Throwable cause = e.getCause();
        if (e instanceof MongoCommandFailureException) {
            return new CommandFailureException((MongoCommandFailureException) e);
        } else if (e instanceof org.mongodb.BulkWriteException) {
            return BulkWriteHelper.translateBulkWriteException((org.mongodb.BulkWriteException) e, decoder);
        } else if (e instanceof com.mongodb.MongoException) {
            return (com.mongodb.MongoException) e;
        } else {
            return new MongoException(e.getMessage(), cause);
        }
    }

    private MongoExceptions() {
    }
}
