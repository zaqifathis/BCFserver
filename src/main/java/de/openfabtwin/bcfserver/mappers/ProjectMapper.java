package de.openfabtwin.bcfserver.mappers;

import de.openfabtwin.bcfserver.dto.ProjectGET;
import de.openfabtwin.bcfserver.dto.ProjectPUT;
import de.openfabtwin.bcfserver.entities.ProjectEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectEntity toEntity(ProjectPUT dto);
    ProjectGET toDto(ProjectEntity entity);

}
