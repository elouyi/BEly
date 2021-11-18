package com.elouyi.bely.data.internal.repository

import io.ktor.client.*


public interface RepositoryBase {

    public val client: HttpClient
}
