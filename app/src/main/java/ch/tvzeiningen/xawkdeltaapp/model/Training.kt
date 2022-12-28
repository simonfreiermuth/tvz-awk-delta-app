package ch.tvzeiningen.xawkdeltaapp.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class Training(
    val date: LocalDate,
    val people: MutableSet<Person> = mutableSetOf()
)

fun Training.dateString(): String =
    date.format(
        DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
    )
