package de.openfabtwin.services;

import de.openfabtwin.services.ViewpointService.ImageType;

public record ImageResult(
        ImageType imageType,
        byte[] imageData
) {}
