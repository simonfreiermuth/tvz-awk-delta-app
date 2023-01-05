package ch.tvzeiningen.awkdeltaapp.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class Training(
    val date: LocalDate,
    val registered: MutableSet<Person> = mutableSetOf(),
    val unregistered: MutableSet<Person> = mutableSetOf(),
    val attendant: MutableSet<Person> = mutableSetOf()
)

fun Training.dateString(): String =
    date.format(
        DateTimeFormatter
            .ofLocalizedDate(FormatStyle.MEDIUM)
    )
