package ch.tvzeiningen.xawkdeltaapp

import androidx.compose.ui.text.toLowerCase
import ch.tvzeiningen.xawkdeltaapp.model.Person
import ch.tvzeiningen.xawkdeltaapp.model.Training
import java.io.InputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun parse(inStream: InputStream): List<Training> {
    val reader = inStream.bufferedReader()

    reader.readLine() // skip empty line
    reader.readLine() // skip title line
    reader.readLine() // skip empty line

    val dates = listOf(reader.readLine(), reader.readLine())
        .dateList()

    val trainings = mutableMapOf<LocalDate, Training>()

    dates.forEach { date ->
        trainings.put(date, Training(date))
    }

    reader
        .takeUnless { it.readLine().normalize().person() == null }
        ?.forEachLine { line ->
            val normalized = line.normalize()
            val person = normalized.person() ?: return@forEachLine

            normalized
                .registrationList()
                .forEachIndexed { index, isRegistred ->
                    if (isRegistred) {
                        val date = dates[index] // map bool to training date
                        trainings[date]?.people?.add(person) // add person to training
                    }
                }
        }

    // TODO: read last line to verify parsing

    return trainings.values.toList()
}

fun List<String>.dateList(): List<LocalDate> {
    if (this.size < 2) error("list must contain months and days")

    val dateFormatterMonths = DateTimeFormatter
        .ofPattern("d MMMM u")
        .withLocale(Locale.UK)

    val months = this[0]
        .normalize()
        .map { raw ->
            if (raw.isNotBlank()) LocalDate.parse(
                "1 $raw",
                dateFormatterMonths
            )
            else null
        }
        .drop(1) // skip first, empty row

    var lastMonth = months.firstNotNullOf { it }

    val dateFormatterDays = DateTimeFormatter
        .ofPattern("E dd M u")
        .withLocale(Locale.UK)

    return this[1]
        .normalize()
        .filter { line -> line.isNotBlank() }
        .mapIndexed { index, raw ->
            if (months[index] != null) lastMonth = months[index]!!
            LocalDate.parse(
                "$raw${lastMonth.monthValue} ${lastMonth.year}",
                dateFormatterDays
            )
        }
        .filterNotNull()
}

fun String.normalize(): List<String> = this
    .replace("\"", "") // replace " around values
    .split(",")

fun List<String>.person(): Person? {
    val raw = this[0]
    if (raw.isBlank()) return null
    return Person(raw)
}

fun List<String>.registrationList(): List<Boolean> = this
    .drop(1) // skip first row (name)
    .map { raw -> raw.lowercase().trim() == "yes" }