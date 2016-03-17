package net.seanamos.flowsample.data;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import net.seanamos.flowsample.data.model.Person;
import net.seanamos.flowsample.data.network.response.PagingResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class PagingResponseTypeAdapter implements TypeAdapterFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> token) {
        if (PagingResponse.class.isAssignableFrom(token.getRawType())) {
            Type type = token.getType();
            if (type instanceof ParameterizedType) {
                ParameterizedType param = (ParameterizedType) type;
                Type[] instantiated = param.getActualTypeArguments();
                if (instantiated.length == 1 && Person.class.isAssignableFrom(TypeToken.get(instantiated[0]).getRawType())) {
                    return (TypeAdapter<T>) gson.getDelegateAdapter(this, new TypeToken<PagingResponse<Person>>(){});
                }
            }
        }
        return null;
    }

}