package net.seanamos.flowsample.data.network;

import net.seanamos.flowsample.data.network.response.PagingResponse;
import net.seanamos.flowsample.data.model.Person;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface SWService {

    @GET("api/people")
    Observable<PagingResponse> fetchPeople();

    @GET("api/people/{person}")
    Observable<Person> fetchPerson(@Path("person") int id);

}
