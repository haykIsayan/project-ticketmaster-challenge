package com.example.project_ticketmaster_challenge.common.view_model

sealed class ViewModelState<DataType> {

    data class ViewModelStatePending<DataType>(val defaultValue: DataType? = null): ViewModelState<DataType>()

    data class ViewModelStateError<DataType>(val message: String? = null): ViewModelState<DataType>()

    data class ViewModelStateEmpty<DataType>(val message: String? = null): ViewModelState<DataType>()

    data class ViewModelStateIdle<DataType>(val value: DataType): ViewModelState<DataType>()
}
