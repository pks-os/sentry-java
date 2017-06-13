package io.sentry.marshaller.json;

import com.fasterxml.jackson.core.JsonGenerator;
import io.sentry.event.interfaces.DebugMetaInterface;
import io.sentry.event.interfaces.ExceptionInterface;

import java.io.IOException;

/**
 * Binding that converts a {@link DebugMetaInterface} to a JSON stream.
 */
public class DebugMetaInterfaceBinding implements InterfaceBinding<DebugMetaInterface> {
    private static final String DEBUG_META = "debug_meta";
    private static final String IMAGES = "images";
    private static final String UUID = "uuid";
    private static final String TYPE = "type";

    @Override
    public void writeInterface(JsonGenerator generator, DebugMetaInterface debugMetaInterface) throws IOException {
        generator.writeStartObject();
        generator.writeObjectFieldStart(DEBUG_META);
        writeDebugImages(generator, debugMetaInterface);
        generator.writeEndObject();
        generator.writeEndObject();
    }

    private void writeDebugImages(JsonGenerator generator, DebugMetaInterface debugMetaInterface) throws IOException {
        generator.writeArrayFieldStart(IMAGES);
        for (DebugMetaInterface.DebugImage debugImage : debugMetaInterface.getDebugImages()) {
            generator.writeStartObject();
            generator.writeStringField(UUID, debugImage.getUuid());
            generator.writeStringField(TYPE, debugImage.getType());
            generator.writeEndObject();
        }
        generator.writeEndArray();
    }
}
