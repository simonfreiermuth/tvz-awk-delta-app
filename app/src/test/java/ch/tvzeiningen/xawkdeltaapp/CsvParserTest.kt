package ch.tvzeiningen.xawkdeltaapp

import ch.tvzeiningen.xawkdeltaapp.model.Person
import ch.tvzeiningen.xawkdeltaapp.model.Training
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CsvParserTest {

    private val CH_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.M.uuuu")

    @Test
    fun testParse() {
        // given
        val res = CsvParserTest::class.java.getResource("/xoyondo.csv")

        // when
        val trainings = res?.openStream()?.use { input -> parse(input) } ?: return fail("Could not read open test data")

        val simon = Person("Simon")
        val training1 = Training(LocalDate.parse("02.11.2022", CH_DATE_FORMATTER))
        val training14 = Training(LocalDate.parse("23.12.2022", CH_DATE_FORMATTER))

        // then
        assertEquals(14, trainings.size)
        assertNotNull(
            trainings.firstOrNull() { t -> t.date == training1.date }
        )
        assertNotNull(
            trainings.firstOrNull() { t -> t.date == training14.date }
        )

        assertTrue(
            trainings
                .first { t -> t.date == training1.date }
                .people.contains(simon)
        )
        assertEquals(
            12,
            trainings
                .first { t -> t.date == training14.date }
                .people.size
        )
    }

    @Test
    fun testDataList() {
        // given
        val line1 = """
            "","November 2022","","","","","","","December 2022","","","","","",""
        """.trimIndent()
        val line2 = """
            "","Wed 02 ","Wed 09 ","Wed 16 ","Fri 18 ","Wed 23 ","Fri 25 ","Wed 30 ","Fri 02 ","Wed 07 "
        """.trimIndent()

        val expOut = listOf(
            LocalDate.parse("02.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("09.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("16.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("18.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("23.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("25.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("30.11.2022", CH_DATE_FORMATTER),
            LocalDate.parse("02.12.2022", CH_DATE_FORMATTER),
            LocalDate.parse("07.12.2022", CH_DATE_FORMATTER)
        )

        // when
        val out = listOf(line1, line2).dateList()

        // then
        assertEquals(expOut.size, out.size)
        assertArrayEquals(expOut.toTypedArray(), out.toTypedArray())

    }

    @Test
    fun testNormalize() {
        // given
        val input = """
            "","Wed 02 ","Wed 09 ","Wed 16 ","Fri 18 ","Wed 23 ","Fri 25 ","Wed 30 ","Fri 02 ","Wed 07 ","Fri 09 ","Wed 14 ","Fri 16 ","Wed 21 ","Fri 23 "
        """.trimIndent()

        // when
        val normalized = input.normalize()

        // then
        assertEquals(15, normalized.size)
        assertEquals(
            0,
            normalized.filter { it.contains("\"") }.size
        )
    }

    @Test
    fun testPerson() {
        // given
        val inputWithName = """
            "Simon","Yes","No"
        """.trimIndent()
        val inputWithoutName = """
            "", "1", "3"
        """.trimIndent()


        // when
        val person = inputWithName
            .normalize()
            .person()
        val noPerson = inputWithoutName
            .normalize()
            .person()

        // then
        assertEquals(
            Person("Simon"),
            person
        )
        assertNull(noPerson)
    }

    @Test
    fun testRegistrationList() {
        // given
        val raw = """
            "Simon","Yes","No","Yes","?"
        """.trimIndent()

        // when
        val registrations = raw
            .normalize()
            .registrationList()

        // then
        assertEquals(
            2,
            registrations.filter { it }.size
        )
        assertArrayEquals(
            arrayOf(true, false, true, false),
            registrations.toTypedArray()
        )
    }
}