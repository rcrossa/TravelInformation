package com.transport.transport.service;

import com.transport.transport.model.Path;
import com.transport.transport.repository.PathRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class PathService {

    @Autowired
    public PathRepository pathRepository;

    public Path savePath(Path path) {
        return pathRepository.save(path);
    }

    public Iterable<Path> getAllPaths() {
        return pathRepository.findAll();
    }

    public Path getPath(Long id) {
        return pathRepository.findById(id).orElse(null);
    }


    public Path updatePath(Long id, Path updatedPath) {
        if (pathRepository.existsById(id)) {
            updatedPath.setId(id);
            return pathRepository.save(updatedPath);
        }
        return null; // O lanzar una excepción si prefieres
    }

    public boolean deletePath(Long id) {
        if (pathRepository.existsById(id)) {
            pathRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Map<String, Object> findOptimalPath(Long sourceId, Long destinationId) {
        // Construir el grafo a partir de los caminos en la base de datos
        Map<Long, Map<Long, Double>> graph = new HashMap<>();
        for (Path path : pathRepository.findAll()) {
            graph.computeIfAbsent(path.getSourceId(), k -> new HashMap<>()).put(path.getDestinationId(), path.getCost());
            graph.computeIfAbsent(path.getDestinationId(), k -> new HashMap<>()).put(path.getSourceId(), path.getCost());
        }

        // Algoritmo de Dijkstra
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));
        Map<Long, Double> dist = new HashMap<>();
        Map<Long, Long> prev = new HashMap<>();
        pq.add(new double[]{sourceId, 0.0});
        dist.put(sourceId, 0.0);

        while (!pq.isEmpty()) {
            double[] curr = pq.poll();
            long node = (long) curr[0];
            double cost = curr[1];

            if (node == destinationId) break;

            if (cost > dist.getOrDefault(node, Double.MAX_VALUE)) continue;

            Map<Long, Double> neighbors = graph.getOrDefault(node, new HashMap<>());
            for (Map.Entry<Long, Double> neighbor : neighbors.entrySet()) {
                long nextNode = neighbor.getKey();
                double newCost = cost + neighbor.getValue();

                if (newCost < dist.getOrDefault(nextNode, Double.MAX_VALUE)) {
                    dist.put(nextNode, newCost);
                    prev.put(nextNode, node);
                    pq.add(new double[]{nextNode, newCost});
                }
            }
        }

        // Reconstruir el camino óptimo
        List<Long> path = new ArrayList<>();
        double totalCost = dist.getOrDefault(destinationId, Double.MAX_VALUE);
        if (totalCost == Double.MAX_VALUE) {
            return Map.of("path", Collections.emptyList(), "cost", totalCost);
        }

        for (Long at = destinationId; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return Map.of("path", path, "cost", totalCost);
    }

}
