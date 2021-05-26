FROM nginx

WORKDIR /usr/share/nginx/html

# Remove default nginx static assets
RUN rm -rf ./*

# Copy nginx config for route rewrite rule
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy static assets from builder stage
COPY dist .

# Containers run nginx with global directives and daemon off
ENTRYPOINT ["nginx", "-g", "daemon off;"]
