package ch.tvzeiningen.awkdeltaapp.data

import ch.tvzeiningen.awkdeltaapp.model.Training
import java.time.LocalDate

interface TrainingDataConnector {
    fun getTrainings(): List<Training>

    /**
     * Returns training on a given date or `null`, if not present
     */
    fun getTraining(date: LocalDate): Training?
}