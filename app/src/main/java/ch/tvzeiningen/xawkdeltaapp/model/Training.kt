package ch.tvzeiningen.xawkdeltaapp.model

import java.time.LocalDate

data class Training(
    val date: LocalDate,
    val people: MutableSet<Person> = mutableSetOf()
)
