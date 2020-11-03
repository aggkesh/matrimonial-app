package com.example.matrimonial_app.ui.main

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * Helper sealed class to represent three potential states of a screen, [Loading], [Error], and [Data]
 * Takes a type param for each possible state so you can provide data in all states to differentiate [Loading] and [Error]
 * cases.
 *
 * @param E the type of error data
 * @param L the type of loading data
 * @param T the type of the screen data
 */
sealed class ScreenState<out E, out L, out T> {
    /**
     * Class for the loading state, potentially containing [message]
     */
    data class Loading<out L>(val message: L? = null) : ScreenState<Nothing, L, Nothing>()

    /**
     * Class for an error state, potentially containing [error] of type [E]
     */
    data class Error<out E>(val error: E? = null) : ScreenState<E, Nothing, Nothing>()

    /**
     * Class for an data state, containing [data] of type [T]
     *
     * Note: [data] isn't explicitly nullable and has no default value because it is unlikely to have a data state with no
     * data. If you have a use case for a possible null [data] you can simply use a nullable [T]
     */
    data class Data<out T>(val data: T) : ScreenState<Nothing, Nothing, T>()
}