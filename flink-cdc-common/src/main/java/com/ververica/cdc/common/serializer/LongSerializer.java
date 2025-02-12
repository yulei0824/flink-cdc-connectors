/*
 * Copyright 2023 Ververica Inc.
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

package com.ververica.cdc.common.serializer;

import org.apache.flink.api.common.typeutils.SimpleTypeSerializerSnapshot;
import org.apache.flink.api.common.typeutils.TypeSerializerSnapshot;
import org.apache.flink.api.common.typeutils.base.TypeSerializerSingleton;
import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.core.memory.DataOutputView;

import java.io.IOException;

/** Type serializer for {@code Long}. */
public final class LongSerializer extends TypeSerializerSingleton<Long> {

    private static final long serialVersionUID = 1L;

    /** Sharable instance of the LongSerializer. */
    public static final LongSerializer INSTANCE = new LongSerializer();

    private static final Long ZERO = 0L;

    @Override
    public boolean isImmutableType() {
        return true;
    }

    @Override
    public Long createInstance() {
        return ZERO;
    }

    @Override
    public Long copy(Long from) {
        return from;
    }

    @Override
    public Long copy(Long from, Long reuse) {
        return from;
    }

    @Override
    public int getLength() {
        return Long.BYTES;
    }

    @Override
    public void serialize(Long record, DataOutputView target) throws IOException {
        target.writeLong(record);
    }

    @Override
    public Long deserialize(DataInputView source) throws IOException {
        return source.readLong();
    }

    @Override
    public Long deserialize(Long reuse, DataInputView source) throws IOException {
        return deserialize(source);
    }

    @Override
    public void copy(DataInputView source, DataOutputView target) throws IOException {
        target.writeLong(source.readLong());
    }

    @Override
    public TypeSerializerSnapshot<Long> snapshotConfiguration() {
        return new LongSerializerSnapshot();
    }

    // ------------------------------------------------------------------------

    /** Serializer configuration snapshot for compatibility and format evolution. */
    @SuppressWarnings("WeakerAccess")
    public static final class LongSerializerSnapshot extends SimpleTypeSerializerSnapshot<Long> {

        public LongSerializerSnapshot() {
            super(() -> INSTANCE);
        }
    }
}
