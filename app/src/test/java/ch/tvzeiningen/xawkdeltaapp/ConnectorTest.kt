package ch.tvzeiningen.xawkdeltaapp

import ch.tvzeiningen.xawkdeltaapp.data.XoyondoConnector
import org.junit.Test

/**
 * This test is intended for the manual testing of a connector implementation while developing.
 */
class ConnectorTest {
    @Test
    fun testXoyondoConnector() {
        val connector = XoyondoConnector()

        connector
            .getTrainings()
            .forEach{t -> println(t)}

    }
}