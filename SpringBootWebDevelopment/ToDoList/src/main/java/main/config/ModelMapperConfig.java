package main.config;

import main.model.Task;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Игнорирование null-значений при копировании
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // Игнорирование неизвестных свойств при копировании
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        // Использование стратегии строгого соответствия для копирования полей
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // Добавляем кастомный конвертер для обработки пустых строк (пустые значения полей)
        modelMapper.addConverter(new EmptyStringConverter());

        // Добавляем кастомный конвертер для обработки значения isDone
        modelMapper.addConverter(new IsDoneConverter());

        modelMapper.createTypeMap(Task.class, Task.class).addMappings(mapping -> mapping.skip(Task::setId));
        return modelMapper;
    }

    // Кастомный конвертер для обработки пустых строк (пустые значения полей)
    private static class EmptyStringConverter implements Converter<String, String> {
        @Override
        public String convert(MappingContext<String, String> context) {
            String source = context.getSource();
            if (source != null && source.trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty field value is not allowed.");
            }
            return source;
        }
    }

    // Кастомный конвертер для обработки значения isDone
    private static class IsDoneConverter implements Converter<Object, Boolean> {
        @Override
        public Boolean convert(MappingContext<Object, Boolean> context) {
            Object source = context.getSource();
            if (source == null) {
                return false; // Значение null будет преобразовано в false
            } else if (source instanceof String) {
                String stringValue = ((String) source).trim();
                if (stringValue.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "isDone field value cannot be empty.");
                } else if ("true".equalsIgnoreCase(stringValue)) {
                    return true;
                } else if ("false".equalsIgnoreCase(stringValue)) {
                    return false;
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for isDone. Use 'true' or 'false'.");
                }
            } else {
                try {
                    return (Boolean) source;
                } catch (ClassCastException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value for isDone. Use 'true' or 'false'.");
                }
            }
        }
    }

}
