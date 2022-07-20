export interface IViewLog {
  id?: number;
  actionName?: string | null;
  entityName?: string | null;
  entityId?: string | null;
}

export class ViewLog implements IViewLog {
  constructor(public id?: number, public actionName?: string | null, public entityName?: string | null, public entityId?: string | null) {}
}

export function getViewLogIdentifier(viewLog: IViewLog): number | undefined {
  return viewLog.id;
}
