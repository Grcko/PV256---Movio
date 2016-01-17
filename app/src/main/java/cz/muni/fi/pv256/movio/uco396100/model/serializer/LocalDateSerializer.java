package cz.muni.fi.pv256.movio.uco396100.model.serializer;

import com.activeandroid.serializer.TypeSerializer;

import org.joda.time.LocalDate;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by oliver on 17/01/16.
 */
public class LocalDateSerializer extends TypeSerializer {

    @Override
    public Class<?> getDeserializedType() {
        return LocalDate.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override
    public String serialize(Object data) {
        if (data == null) {
            return null;
        }

        return ISODateTimeFormat.date().print((LocalDate) data);
    }

    @Override
    public LocalDate deserialize(Object data) {
        if (data == null) {
            return null;
        }

        return LocalDate.parse(((String) data), ISODateTimeFormat.date());
    }


}
