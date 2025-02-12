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

package com.ververica.cdc.common.factories;

import org.apache.flink.configuration.ConfigOption;

import com.ververica.cdc.common.annotation.PublicEvolving;

import java.util.Set;

/**
 * Base interface for all kind of factories that create object instances from a list of key-value
 * pairs in Flink CDC DataSource & DataSink API.
 *
 * <p>A factory is uniquely identified by {@link Class} and {@link #identifier()}.
 *
 * <p>The list of available factories is discovered using Java's Service Provider Interfaces (SPI).
 * Classes that implement this interface can be added to {@code
 * META_INF/services/com.ververica.cdc.common.factories.Factory} in JAR files.
 *
 * <p>Every factory declares a set of required and optional options. This information will not be
 * used during discovery but is helpful when generating documentation and performing validation. A
 * factory may discover further (nested) factories, the options of the nested factories must not be
 * declared in the sets of this factory.
 *
 * <p>It is the responsibility of each factory to perform validation before returning an instance.
 */
@PublicEvolving
public interface Factory {

    /** Returns a unique identifier among same factory interfaces. */
    String identifier();

    /**
     * Returns a set of {@link ConfigOption} that an implementation of this factory requires in
     * addition to {@link #optionalOptions()}.
     */
    Set<ConfigOption<?>> requiredOptions();

    /**
     * Returns a set of {@link ConfigOption} that an implementation of this factory consumes in
     * addition to {@link #requiredOptions()}.
     */
    Set<ConfigOption<?>> optionalOptions();
}
