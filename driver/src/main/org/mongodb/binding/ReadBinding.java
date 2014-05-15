package org.mongodb.binding;

import org.mongodb.ReadPreference;

/**
 * A factory of connection sources to servers that can be read from and that satisfy the specified read preference.
 *
 * @since 3.0
 */
public interface ReadBinding extends ReferenceCounted {
    /**
     * The read preference that all connection sources returned by this instance will satisfy.
     * @return the non-null read preference
     */
    ReadPreference getReadPreference();

    /**
     * Returns a connection source to a server that satisfies the specified read preference.
     * @return the connection source
     */
    ConnectionSource getReadConnectionSource();

    @Override
    ReadBinding retain();
}