
# Elastic Search

This repository contains code for a simple Spring Boot application that uses Elasticsearch to store and search data. The Docker Compose file is included to easily spin up an Elasticsearch instance.

# Prerequisites
* Docker
* Docker Compose

# Installation

Clone this repository.
Navigate to the root of the cloned repository.
Run docker-compose up -d to start the Elasticsearch instance.
Run the Spring Boot application.

# Usage
The application provides a RESTful API to interact with Elasticsearch. Here are the available endpoints:

## GET /api/v1/getCompaniesByEmployeesName?name={name}
This endpoint returns a list of companies whose employees have the given name. The parameter name is required.

## POST /api/v1/postCompany
This endpoint adds a new company to Elasticsearch. The request body should be a JSON object representing a Company object.

## GET /api/v1/search?search={searchTerm}
This endpoint performs a basic text search on the description field of the Company objects. The parameter searchTerm is required.

## GET /api/v1/score-search?search={searchTerm}
This endpoint performs a text search on the description field of the Company objects and returns results that match 100% of the search term. The parameter searchTerm is required.

## GET /api/v1/full-search?search={searchTerm}
This endpoint performs a text search on the description field of the Company objects and requires all terms to be present in the results. The parameter searchTerm is required.

## GET /api/v1/fuzzy-search?search={searchTerm}
This endpoint performs a fuzzy text search on the description field of the Company objects with a fuzziness of one and a prefix length of two. The parameter searchTerm is required.

## GET /api/v1/phrase-search?search={searchTerm}
This endpoint performs a phrase search on the description field of the Company objects with a slop of one. The parameter searchTerm is required.

# Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

# License
This project is licensed under the MIT License - see the LICENSE file for details.
