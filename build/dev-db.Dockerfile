FROM postgres:16.2-bookworm

COPY . /docker-entrypoint-initdb.d/