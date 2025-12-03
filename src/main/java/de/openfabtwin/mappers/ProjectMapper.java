package de.openfabtwin.mappers;

import de.openfabtwin.dto.ProjectGET;
import de.openfabtwin.entities.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProjectMapper {
    @Mapping(source = "guid", target = "projectId")
    ProjectGET toDto(ProjectEntity entity);
}
