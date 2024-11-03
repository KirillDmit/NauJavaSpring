package ru.dmitriev.NauJavaSpring.services;

public interface RoleService
{
    /**
     * Удаляет роль по имени
     * @param roleTitle имя роли
     */
    void deleteRole(String roleTitle);
}
